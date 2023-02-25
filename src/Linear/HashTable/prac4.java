package Linear.HashTable;

// 해시 충돌 해결 - 개방 주소법 (이중 해싱)

class MyHashTable4 extends MyHashTable {
    int c;

    public MyHashTable4(int size) {
        super(size);
        this.c = getHashC(size);
    }

    // 해시 테이블의 size 보다 조금 작은 소수 반환
    public int getHashC(int size) {
        int c = 0;

        if (size <= 2) {
            return size;
        }

        for (int i = size - 1; i > 2; i--) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                c = i;
                break;
            }
        }
        return c;
    }

    // 두 번째 해시 함수
    // 테이블의 사이즈와 두번 째 해시값의 관계가 서로소일 때 좋은 성능을 보인다
    // 테이블의 사이즈를 소수로 선택하면 위 문제는 해결됨
    public int getHash2(int key) {
        return 1 + key % this.c;
    }

    @Override
    public void setValue(int key, int data) {
        int idx = this.getHash(key);

        if (elemCnt == this.table.length) {
            System.out.println("Hash Table is Full!");
            return;
        } else if (this.table[idx] == null) {
            this.table[idx] = data;
        } else {
            int newIdx = idx;
            int cnt = 1;

            while (true) {
                newIdx = (newIdx + this.getHash2(newIdx) * cnt) % this.table.length;

                if (this.table[newIdx] == null) {
                    break;
                }
                cnt++;
            }
            this.table[newIdx] = data;
        }
        this.elemCnt++;
    }
}

public class prac4 {
    public static void main(String[] args) {
        // Test code
        MyHashTable4 ht = new MyHashTable4(11);
        ht.setValue(1, 10);
        ht.setValue(2, 20);
        ht.setValue(3, 30);
        ht.printHashTable();


        ht.setValue(1, 100);
        ht.setValue(1, 200);
        ht.setValue(1, 300);
        ht.printHashTable();
    }
}