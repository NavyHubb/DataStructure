package Linear.HashTable;

// 해시 충돌 해결 - 개방 주소법 (선형 탐사법)

class MyHashTable2 extends MyHashTable {
    public MyHashTable2(int size) {
        super(size);
    }

    @Override
    public void setValue(int key, int data) {
        int idx = getHash(key);

        if (elemCnt == this.table.length) {
            System.out.println("Hash Table is Full!");
            return;
        } else if (this.table[idx] == null) {
            this.table[idx] = data;
        } else {
            int newIdx = idx;

            while (true) {
                newIdx = (newIdx + 1) % this.table.length;

                if (this.table[newIdx] == null) {
                    this.table[newIdx] = data;
                    break;
                }
            }
        }
        elemCnt++;
    }
}

public class prac2 {
    public static void main(String[] args) {
        // Test code
        MyHashTable2 ht = new MyHashTable2(5);
        ht.setValue(1, 1);
        ht.setValue(3, 3);
        ht.printHashTable();

        ht.setValue(1, 10);
        ht.printHashTable();

        ht.setValue(1, 20);
        ht.setValue(1, 30);
        ht.setValue(1, 40);
        ht.printHashTable();
    }
}
