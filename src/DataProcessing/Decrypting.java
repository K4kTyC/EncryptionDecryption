package src.DataProcessing;

import src.EncDecAlgorithms.IEncDecAlgorithm;

public class Decrypting extends DataProcessing {

    public Decrypting(IEncDecAlgorithm algorithm) {
        super(algorithm);
    }

    public String processData(String data, int key) {
        return algorithm.decrypt(data, key);
    }
}
