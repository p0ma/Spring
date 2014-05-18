package system;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import system.decision.support.logic.Conclusion;
import system.decision.support.logic.inference.Inference;
import system.decision.support.logic.inference.InferenceMachine;
import system.decision.support.logic.operations.GreaterEqual;
import system.decision.support.logic.IPredicate;
import system.decision.support.logic.Predicate;
import system.drilling.model.IModel;
import system.drilling.model.Model;
import system.drilling.model.parameters.actual.parameters.MudVolume;
import system.drilling.model.parameters.actual.parameters.Volume;
import system.drilling.model.parameters.actual.parameters.Volume1;
import system.drilling.model.parameters.actual.parameters.Volume2;
import system.drilling.model.well.Casing;
import system.drilling.model.well.Well;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class Main {

    public static void main(String[] args) {

        final ApplicationContext modelContext = new AnnotationConfigApplicationContext(ModelContextConfiguration.class);

        Casing casing = modelContext.getBean(Casing.class);

        casing.setHeight(2);
        casing.setWidth(3);
        Well well = modelContext.getBean(Well.class);
        System.out.println(well.getValue());


        IModel model = new Model(modelContext);
        model.setParameterValue(MudVolume.class, new Double(200))
                .setParameterValue(Volume.class, new Double(60));

        Set<Map.Entry<String,String>> entrySet = model.setParameterValue(Volume1.class, new Double(30)).getAllValues().entrySet();

        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        for (Map.Entry<String, String> entry : model.setParameterValue(Volume1.class, new Double(30))
                .getAllValues().entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        IPredicate predicate1 = new Predicate(
                new GreaterEqual(model.getParameter(Volume2.class), model.getParameter(MudVolume.class))
        );
        predicate1.setName("Volume2 Check");

        Predicate predicate2 = new Predicate(
                new GreaterEqual(model.getParameter(Volume1.class), model.getParameter(MudVolume.class))
        );
        predicate2.setName("Volume1 Check");

        Predicate predicate3 = new Predicate();
        predicate3.setConclusion((new Conclusion("Method1 is acceptable")));
        predicate2.setFiresOnFalse(predicate3);
        predicate1.setFiresOnFalse(predicate2);

        InferenceMachine inferenceMachine = new InferenceMachine();

        inferenceMachine.doInference(predicate1);

        Inference inference = inferenceMachine.getInference();

        System.out.println(inference.getInferenceDetails());

    }

    public static List<Object> getInstantiatedSigletons(ApplicationContext ctx) {
        List<Object> singletons = new ArrayList<Object>();

        String[] all = ctx.getBeanDefinitionNames();

        ConfigurableListableBeanFactory clbf = ((AbstractApplicationContext) ctx).getBeanFactory();
        for (String name : all) {
            Object s = clbf.getSingleton(name);
            if (s != null)
                singletons.add(s);
        }

        return singletons;
    }
}
