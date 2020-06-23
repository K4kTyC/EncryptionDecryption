package src.DataProcessing;

import src.encdec.IEncDecAlgorithm;

class Encrypting extends DataProcessing {

    public Encrypting(IEncDecAlgorithm algorithm) {
        super(algorithm);
    }

    protected String processData(String data, int key) {
        return algorithm.encrypt(data, key);
    }
}
