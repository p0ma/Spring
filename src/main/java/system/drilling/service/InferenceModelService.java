package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.decision.support.logic.InferenceModel;
import system.drilling.repositories.InferenceModelRepository;
import system.drilling.repositories.exceptions.InferenceModelFoundException;

import java.util.List;

@Service
public class InferenceModelService {

    @Autowired
    private InferenceModelRepository inferenceModelRepository;

    @Transactional
    public InferenceModel create(InferenceModel created) {
        return inferenceModelRepository.save(created);
    }

    @Transactional(rollbackFor = InferenceModelFoundException.class)
    public InferenceModel delete(Long inferenceModelId) throws InferenceModelFoundException {
        InferenceModel deleted = inferenceModelRepository.findOne(inferenceModelId);

        if (deleted == null) {
            throw new InferenceModelFoundException("No predicate with id " + inferenceModelId + " has been found. Nothing to delete.");
        }

        inferenceModelRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<InferenceModel> findAll() {
        return inferenceModelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public InferenceModel findById(Long id) {
        return inferenceModelRepository.findOne(id);
    }

    @Transactional(rollbackFor = InferenceModelFoundException.class)
    public InferenceModel update(InferenceModel updated) throws InferenceModelFoundException {
        InferenceModel inferenceModel = inferenceModelRepository.findOne(updated.getId());

        if (inferenceModel == null) {
            throw new InferenceModelFoundException("No inference parametersModel with id " + updated.getId() + " has been found. Nothing to update.");
        }

        inferenceModelRepository.save(updated);

        return updated;
    }

    public InferenceModel getInferenceModel() {
        List<InferenceModel> parametersModelList = inferenceModelRepository.findAll();
        if (!parametersModelList.isEmpty()) {
            return parametersModelList.get(0);
        } else {
            InferenceModel inferenceModel = new InferenceModel();
            inferenceModelRepository.save(inferenceModel);
            return inferenceModel;
        }
    }
}
