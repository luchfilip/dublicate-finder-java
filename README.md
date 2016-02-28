### Dublicate file finder in Java

####Topic:​Cryptographic hashes

####Objective 
​Understand the purpose of hashing algorithms and create a tool that solves a problem using one of them.​

####Task
​Elaborate a duplicate finder, which analyzes a given directory and prints a list of identical files that have different names or paths.

####Background
When using the same hashing algorithm for any data, the obtained digest will always have a fixed length according to the algorithm.
Several well known hashing algorithms are md5 (16 bytes), sha1 (20 bytes), sha256 (32 bytes), sha512 (64 bytes).
Hashing algorithms properties:

    * determinism = for a given input value it must always generate the same hash value
    * non­invertible = it’s not possible to reconstruct the original input from its hash value
    * continuity = two inputs that differ by a little should be mapped to equal or nearly equal hash values
    * data normalization = e.g. ignore the distinction between upper and lower case letters ­ defined range = output need to be of a fixed size
    * uniformity = should map the expected inputs as evenly as possible over its output range
    
Sometimes there may occur a hash collision when two distinct pieces of data have the same hash value, checksum, fingerprint, or cryptographic digest.
If you have more items to hash than you have slots, then you'll have hash collisions. When
using a poor hashing algorithm, then you'll see collisions even when the items / slots ratio is very small. A good hashing algorithm will attempt to spread the resulting hashes over the entire output space as evenly as possible, and thus minimize collisions.


####Solution
A GUI Java Application was implemented in order to find dublicate files in same folder using MD5 hashes.

![](http://i.imgur.com/E8OvmBa.gif)

####MD5 Checksum

The core method that actually creates checksum from a given file is the following:


            public static byte[] createChecksum(String filename) throws Exception {
                InputStream fis =  new FileInputStream(filename);
        
                byte[] buffer = new byte[1024];
                MessageDigest complete = MessageDigest.getInstance("MD5");
                int numRead;
                do {
                    numRead = fis.read(buffer);
                    if (numRead > 0) {
                        complete.update(buffer, 0, numRead);
                    }
                } while (numRead != -1);
                fis.close();
                return complete.digest();
            }
            
The rest of the methods were implemented in order to easier see how this works in practice.
 
####Conclusion
MD5 hash is not a secure way of hashing data, but for simple tasks like this one, it is a good and fast way of solving the problem. 