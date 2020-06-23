# EncryptionDecryption
EncryptionDecryption project from JetBrains Academy (Java dev).

Program encrypts and decrypts provided data using simple algorithms.
It interacts with user through CLI.
Program works differently, according to provided arguments.

## Arguments
1. `-mod` ***Target operation (enc - encryption, dec - decryption)*** | _default = enc_
2. `-key` ***Algorithm shifts each letter by this number*** | _default = 0_
3. `-data` ***String to be encrypted / decrypted*** | _default = ""_
4. `-in` ***Full name of a file to read data*** | _If there are both -data and -in arguments, program prefers -data over -in_
5. `-out` ***Full name of a file to write the result*** | _If there is no -out argument, program prints data to the standard output_
6. `-alg` ***Specifies algorithm to encrypt / decrypt data (shift, unicode)*** | _default = shift_

## Algorithms
Algorithms used in this project are not suitable for industrial use because they can easily be cracked, but these algorithms demonstrate some general ideas about encryption.

Shift algorithm shifts each letter by the specified number according to its order in the alphabet in circle.
It encodes only English letters (from 'a' to 'z' as the first circle and from 'A' to 'Z' as the second circle i.e. after 'z' comes 'a' and after 'Z' comes 'A').

Unicode algorithm does the same thing but it encodes symbols (from ' ' (space) to '~') according unicode table in circle.
