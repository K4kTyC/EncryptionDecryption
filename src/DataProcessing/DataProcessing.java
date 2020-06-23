package src.DataProcessing;

import src.EncDecAlgorithms.IEncDecAlgorithm;

public abstract class DataProcessing {

    protected IEncDecAlgorithm algorithm;

    public DataProcessing(IEncDecAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public abstract String processData(String data, int key);
}
