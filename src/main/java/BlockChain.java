import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created By IDEA
 *
 * @Author: YJ
 * @Date: 2020/2/18
 * @Time: 22:52
 **/
public class BlockChain {
// 第六步 1
 public static ArrayList<Block> blockchain=new ArrayList<Block>();

// 第九步   变量定义说明见第八步
 public static int difficulty=1;

 public static void main(String[] args) {
/*//  第五步：测试
// 每个区块都根据自己的信息和前一区块的签名，且拥有了自己的数字签名
  Block genesisBlock=new Block("这是第一个区块链","0");
  System.out.println("区块1的hash："+genesisBlock.hash);
  Block secondBlock=new Block("区块链2的hash：",genesisBlock.hash);
  System.out.println("区块2的hash："+secondBlock.hash);
  Block thirdBlock=new Block("区块链3的hash：",secondBlock.hash);
  System.out.println("区块3的hash："+thirdBlock.hash);
  第五步可以先运行测试~~
  */
//第六步 2  add our blocks to the blockchain ArrayList:
  /*blockchain.add(new Block("区块1:","0"));
  blockchain.add(new Block("区块2:",blockchain.get(blockchain.size()-1).hash));
  blockchain.add(new Block("区块3:",blockchain.get(blockchain.size()-1).hash));*/
  // 第九步：九-1  扩展第六步，需要 isChainValid()方法检查每个区块是否通过采矿得到了哈希值
  blockchain.add(new Block("区块1","0"));
    System.out.println("尝试得到区块1...");
    blockchain.get(0).mineBlock(difficulty);
  blockchain.add(new Block("区块2",blockchain.get(blockchain.size()-1).hash));
    System.out.println("尝试得到区块2...");
    blockchain.get(1).mineBlock(difficulty);
  blockchain.add(new Block("区块3",blockchain.get(blockchain.size()-1).hash));
    System.out.println("尝试得到区块3...");
    blockchain.get(2).mineBlock(difficulty);
  System.out.println("\nBlockchain is Valid:"+isChainValid());

//  GsonBuilder gsonBuilder=new GsonBuilder();
  String blockchainJson=new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
  System.out.println("\nThe block chain");
  System.out.println(blockchainJson);
 }

// 第七步 :验证区块链的完整性
  public static Boolean isChainValid(){
   Block currentBlock;//当前区块
   Block previousBlock;//上一个区块

   //九-2
   String hashTarget=new String(new char[difficulty]).replace('\0','0');

   //循环检查区块链
   for(int i=1;i<blockchain.size();i++){
    currentBlock=blockchain.get(i);
    previousBlock=blockchain.get(i-1);
    //比较登记注册的hash和计算得到的hash
       if (!currentBlock.hash.equals(currentBlock.calculateHash())){
        System.out.println("当前hash不相同");
         return false;
       }
       //比较前一个hash和注册的上一个hash
       if (!previousBlock.hash.equals(currentBlock.previousHash)){
        System.out.println("前一个区块hash不同");
        return false;
       }

       //九-3 check if hash is solved
       if (!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
        System.out.println("这个区块不是我的！");
        return false;
      }

   }
//   return false; //第七步
     return true; // 第 九-4
   //第七步:  链中区块发生任意的改变都会使该方法返回

   // 当人们将自己的区块链分享到比特币网络节点后，网络会接收其最长有效链。
   // 是什么阻止攻击者篡改旧区块上的数据，然后创建更长的新区块链并放在网络上呢？
   // 是工作量证明机制。哈希现金工作量证明机制意味着创建新的区块需要相当长的时间和计算能力。
   // 因此攻击者需要掌握巨大的计算能力，比其他所有同行加起来的计算能力还要多。

 }
}
