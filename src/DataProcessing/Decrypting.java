package src.DataProcessing;

import src.encdec.IEncDecAlgorithm;

class Decrypting extends DataProcessing {

    public Decrypting(IEncDecAlgorithm algorithm) {
        super(algorithm);
    }

    protected String processData(String data, int key) {
        return algorithm.decrypt(data, key);
    }
}
