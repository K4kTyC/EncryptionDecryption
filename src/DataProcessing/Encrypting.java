package src.DataProcessing;

import src.EncDecAlgorithms.IEncDecAlgorithm;

public class Encrypting extends DataProcessing {

    public Encrypting(IEncDecAlgorithm algorithm) {
        super(algorithm);
    }

    public String processData(String data, int key) {
        return algorithm.encrypt(data, key);
    }
}
