package src.DataProcessing;

import src.encdec.IEncDecAlgorithm;

abstract class DataProcessing {

    protected IEncDecAlgorithm algorithm;

    public DataProcessing(IEncDecAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    protected abstract String processData(String data, int key);
}
