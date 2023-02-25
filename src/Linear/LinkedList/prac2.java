package Linear.LinkedList;

// prac1의 LinkedList는 데이터의 삽입, 삭제가 연결 리스트의 끝에서만 이루어졌던 것과 달리 이 클래스에서는 연결 리스트의 중간에서도 삽입, 삭제가 이루어지도록 구현

class LinkedList2 extends LinkedList {
    LinkedList2(Node node) {
        this.head = node;  // 현재 클래스에 따로 head 변수를 선언한 게 없기 때문에 이 head는 부모 클래스에서 상속받은 변수
    }

    // 연결 리스트에 데이터 추가
    // before_data가 null인 경우, 가장 뒤에 추가
    // before_data에 값이 있는 경우, 해당 값을 가진 노드 앞에 추가
    public void addData(int data, Integer beforeData) {
        if (this.head == null) {
            this.head = new Node(data, null);
        } else if (beforeData == null) {
            Node cur = this.head;

            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(data, null);
        } else {
            Node cur = this.head;
            Node pre = cur;

            while (cur != null) {
                if (cur.data == beforeData) {
                    if (cur == this.head) {
                        this.head = new Node(data, this.head);
                    } else {
                        pre.next = new Node(data, cur);
                    }
                    break;
                }
                pre = cur;
                cur = cur.next;
            }
        }
    }

    public void removeData(int data) {
        if (this.isEmpty()) {
            System.out.println("List is Empty");
            return;
        }

        Node cur = this.head;
        Node pre = cur;

        while (cur != null) {
            if (cur.data == data) {
                if (cur == this.head) {
                    this.head = cur.next;
                } else {
                    pre.next = cur.next;
                }
                break;
            }

            pre = cur;
            cur = cur.next;
        }
    }
}


public class prac2 {
    public static void main(String[] args) {
        //      Test code
        LinkedList2 myList = new LinkedList2(new Node(1, null));
        myList.showData();         // 1

        myList.addData(2);
        myList.addData(3);
        myList.addData(4);
        myList.addData(5);
        myList.showData();         // 1 2 3 4 5

        myList.addData(100, 1);
        myList.addData(200, 2);
        myList.addData(300, 3);
        myList.addData(400, 4);
        myList.addData(500, 5);
        myList.showData();         // 100 1 200 2 300 3 400 4 500 5

        myList.removeData(300);
        myList.removeData(100);
        myList.removeData(500);
        myList.removeData(200);
        myList.removeData(400);
        myList.showData();         // 1 2 3 4 5

        myList.removeData(3);
        myList.removeData(1);
        myList.removeData(5);
        myList.removeData(2);
        myList.removeData(4);
        myList.showData();         // List is empty!
    }
}
