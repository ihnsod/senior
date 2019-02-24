package com.zxx.senior.basics.j8;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;

/**
 * @author: Aries
 * @create: 2018/12/03 15:15
 **/
public class MapTest {

    public static void main(String[] args) {


        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("hello", "world");

        hashMap.get("hello");



        //java 8 中的 hashMap的 put 方法过程


        /**
         *  public V put(K key, V value) {
         *         return putVal(hash(key), key, value, false, true);
         *     }
         *
         *
         * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
         *                    boolean evict) {
         *         Node<K,V>[] tab; Node<K,V> p; int n, i;
         *
         *    // 步骤①：tab为空则创建
         *
         *         if ((tab = table) == null || (n = tab.length) == 0)
         *             n = (tab = resize()).length;
         *
         *    // 步骤②：计算index，并对null做处理
         *
         *         if ((p = tab[i = (n - 1) & hash]) == null)
         *             tab[i] = newNode(hash, key, value, null);
         *         else {
         *             Node<K,V> e; K k;
         *   // 步骤③：节点key存在，直接覆盖value
         *             if (p.hash == hash &&
         *                 ((k = p.key) == key || (key != null && key.equals(k))))
         *                 e = p;
         *   // 步骤④：判断该链为红黑树
         *             else if (p instanceof TreeNode)
         *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *    // 步骤⑤：该链为链表
         *             else {
         *                 for (int binCount = 0; ; ++binCount) {
         *                     if ((e = p.next) == null) {
         *                         p.next = newNode(hash, key, value, null);
         *     //链表长度大于8转换为红黑树进行处理
         *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         *                             treeifyBin(tab, hash);
         *                         break;
         *                     }
         *     // key已经存在直接覆盖value
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         break;
         *                     p = e;
         *                 }
         *             }
         *             if (e != null) { // existing mapping for key
         *                 V oldValue = e.value;
         *                 if (!onlyIfAbsent || oldValue == null)
         *                     e.value = value;
         *                 afterNodeAccess(e);
         *                 return oldValue;
         *             }
         *         }
         *    // 步骤⑥：超过最大容量 就扩容
         *         ++modCount;
         *         if (++size > threshold)
         *             resize();
         *         afterNodeInsertion(evict);
         *         return null;
         *     }
         */







        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap();


        //java 8 的put方法如下

        /**
         *
         *
         *         public V put(K key, V value) {
         *             return putVal(key, value, false);
         *         }
         *
         *
         *   final V putVal(K key, V value, boolean onlyIfAbsent) {
         *
         *       //如果key为null 或者是 value == null 抛出null指针异常
         *             if (key == null || value == null) throw new NullPointerException();
         *
         *       //计算hash值
         *             int hash = spread(key.hashCode());
         *
         *             int binCount = 0;
         *             for (ConcurrentHashMap.Node<K,V>[] tab = table;;) {
         *                 ConcurrentHashMap.Node<K,V> f; int n, i, fh; K fk; V fv;
         *                 if (tab == null || (n = tab.length) == 0)
         *
         *      // 步骤①：tab为空则创建
         *                     tab = initTable();
         *
         *     //计算新的索引 并对null做处理
         *                 else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
         *                     if (casTabAt(tab, i, null, new ConcurrentHashMap.Node<K,V>(hash, key, value)))
         *                         break;                   // no lock when adding to empty bin
         *                 }
         *                 else if ((fh = f.hash) == MOVED)
         *                     tab = helpTransfer(tab, f);
         *                 else if (onlyIfAbsent // check first node without acquiring lock
         *                         && fh == hash
         *                         && ((fk = f.key) == key || (fk != null && key.equals(fk)))
         *                         && (fv = f.val) != null)
         *                     return fv;
         *                 else {
         *                     V oldVal = null;
         *                     synchronized (f) {
         *                         if (tabAt(tab, i) == f) {
         *                             if (fh >= 0) {
         *                                 binCount = 1;
         *                                 for (ConcurrentHashMap.Node<K,V> e = f;; ++binCount) {
         *                                     K ek;
         *                                     if (e.hash == hash &&
         *                                             ((ek = e.key) == key ||
         *                                                     (ek != null && key.equals(ek)))) {
         *
         *          //// 步骤③：节点key存在，直接覆盖value
         *                                         oldVal = e.val;
         *                                         if (!onlyIfAbsent)
         *                                             e.val = value;
         *                                         break;
         *                                     }
         *                                     ConcurrentHashMap.Node<K,V> pred = e;
         *                                     if ((e = e.next) == null) {
         *                                         pred.next = new ConcurrentHashMap.Node<K,V>(hash, key, value);
         *                                         break;
         *                                     }
         *                                 }
         *                             }
         *                             else if (f instanceof ConcurrentHashMap.TreeBin) {
         *                                 ConcurrentHashMap.Node<K,V> p;
         *                                 binCount = 2;
         *                                 if ((p = ((ConcurrentHashMap.TreeBin<K,V>)f).putTreeVal(hash, key,
         *                                         value)) != null) {
         *                                     oldVal = p.val;
         *                                     if (!onlyIfAbsent)
         *                                         p.val = value;
         *                                 }
         *                             }
         *                             else if (f instanceof ConcurrentHashMap.ReservationNode)
         *                                 throw new IllegalStateException("Recursive update");
         *                         }
         *                     }
         *                     if (binCount != 0) {
         *                         if (binCount >= TREEIFY_THRESHOLD)
         *                             treeifyBin(tab, i);
         *                         if (oldVal != null)
         *                             return oldVal;
         *                         break;
         *                     }
         *                 }
         *             }
         *             addCount(1L, binCount);
         *             return null;
         *         }
         *
         */


        hashMap.get("hello");
    }
}
