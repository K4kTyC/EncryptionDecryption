package src.EncDecAlgorithms;

public interface IEncDecAlgorithm {
    String encrypt(String msg, int key);
    String decrypt(String msg, int key);
}
