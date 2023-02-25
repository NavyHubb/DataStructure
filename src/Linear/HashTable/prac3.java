package Linear.HashTable;

// 해시 충돌 해결 - 개방 주소법 (제곱 탐사법)

class MyHashTable3 extends MyHashTable {
    public MyHashTable3(int size) {
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
            int cnt = 0;

            while (true) {
                newIdx = (newIdx + (int) Math.pow(2, cnt)) % this.table.length;

                if (this.table[newIdx] == null) {
                    this.table[newIdx] = data;
                    break;
                }
                cnt++;
            }
        }
        elemCnt++;
    }
}

public class prac3 {
    public static void main(String[] args) {
        // Test code
        MyHashTable3 ht = new MyHashTable3(11);
        ht.setValue(1, 10);
        ht.setValue(2, 20);
        ht.setValue(4, 40);
        ht.printHashTable();

        ht.setValue(1, 100);
        ht.printHashTable();

        ht.setValue(1, 200);
        ht.setValue(1, 300);
        ht.setValue(1, 400);
        ht.printHashTable();
    }
}
