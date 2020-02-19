import java.util.Date;

/**
 * Created By IDEA
 *
 * @Author: YJ
 * @Date: 2020/2/18
 * @Time: 22:12
 **/
//第一步：创建区块类及其属性
public class Block {
   public String hash; //数字签名
   public String previousHash;//前一区块的哈希
   private String data;//区块数据 our data will be a simple message.
   private long timeStamp;//时间戳as number of milliseconds since 1/1/1970.

 //第八步 开始采矿！！！
 // 八-1
 //要让矿机完成工作量证明机制，即在区块中尝试不同的变量值，直到其哈希值以一定数量的 0 开始。
 //添加一个整数类型的 nonce 向量用于 calculateHash()方法中调用，并添加一个 mineBlock()方法：见 八-2
   private int nonce;

 //block 构造函数
 public Block(String data,String previousHash) {
  this.previousHash = previousHash;
  this.data = data;
  this.timeStamp=new Date().getTime();
  //第四步 hash值获取--调用方法。Making sure we do this after we set the other values.
  this.hash=calculateHash();

 }

// 第三步：计算hash
 public String calculateHash(){
   String calculatedhash=StringUtil.applySha256(
    previousHash+Long.toString(timeStamp)+
     Integer.toString(nonce)+  // 八-2
     data
   );
     return calculatedhash;
 }

 //八-2
 public void mineBlock(int difficulty){
    String target=new String(new char[difficulty]).replace("\0","0");
  //Create a string with difficulty * "0"
    while (!hash.substring(0,difficulty).equals(target)){
      nonce++;
      hash=calculateHash();
    }
  System.out.println("---区块为:"+hash);
  }
  //mineBlock()方法以整数变量 difficulty 作为输入，该变量（难度） 表示需要处理的 0 的数量。
 // 大多数计算机可以快速的处理 1 或 2 个 0 的情况，我建议在测试时处理 4 到 6 个 0。
 // 莱特币的难度大约为 442592。
 //将 difficulty 作为一个静态变量添加到 NoobChain 类中：public static int difficulty = 5;
}
