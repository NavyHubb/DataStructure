package NonLinear.Trie;

import java.util.HashMap;

class Node {
    HashMap<Character, Node> child;
    boolean isTerminal;

    public Node() {
        this.child = new HashMap<>();
        this.isTerminal = false;
    }
}

class Trie {
    Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String str) {
        Node cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            cur.child.putIfAbsent(c, new Node());
            cur = cur.child.get(c);

            if (i == str.length() - 1) {
                cur.isTerminal = true;
                return;
            }
        }
    }

    public boolean search(String str) {
        Node cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (cur.child.containsKey(c)) {
                cur = cur.child.get(c);
            } else {
                return false;
            }

            if (i == str.length() - 1) {
                if (!cur.isTerminal) {
                    return false;
                }
            }
        }

        return true;
    }

    public void delete(String str) {
        boolean result = this.delete(this.root, str, 0);

        if (result) {
            System.out.println(str + "-> 삭제 완료");
        } else {
            System.out.println(str + "-> 해당하는 단어가 없습니다.");
        }
    }

    public boolean delete(Node node, String str, int idx) {
        char c = str.charAt(idx);

        if (!node.child.containsKey(c)) {
            return false;  // 삭제하려는 문자열이 트리에 있지도 않으면 더 볼것도 없지
        }

        Node cur = node.child.get(c);
        idx++;

        if (idx == str.length()) {  // 아래 else문을 통해 재귀적으로 계속 타고 들어가다가 대상 문자열의 끝 문자에 도달했을 때
            if (!cur.isTerminal) {
                return false;  // 대상 문자열(str)에 해당하는 문자가 없는 것
            }

            cur.isTerminal = false;  // 일단 현재 문자로 끝나는 단어는 없애준다

            if (cur.child.isEmpty()) {  // 현재 문자에서부터 파생되는 또 다른 단어가 없을 경우
                node.child.remove(c);  // 현재 문자를 삭제
            }
        } else {  // 대상 문자열에 포함된 문자들을 재귀적으로 탐색해나가는 과정
            if (!this.delete(cur, str, idx)) {
                return false;
            }

            if (!cur.isTerminal && cur.child.isEmpty()) {  // 현재 문자로 끝나는 단어가 없고, 현재 문자로부터 파생되는 문자가 더이상 없을 경우
                node.child.remove(c);  // 현재 문자 삭제
            }
        }

        return true;
    }



}

public class Main {
    public static void main(String[] args) {
        // Test code
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("april");
        trie.insert("app");
        trie.insert("ace");
        trie.insert("bear");
        trie.insert("best");
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("april"));   // true
        System.out.println(trie.search("app"));      // true
        System.out.println(trie.search("ace"));     // true
        System.out.println(trie.search("bear"));    // true
        System.out.println(trie.search("best"));    // true
        System.out.println(trie.search("abc"));     // false

        System.out.println();
        trie.delete("apple");
        System.out.println(trie.search("apple"));   // false
        System.out.println(trie.search("april"));   // true
        System.out.println(trie.search("appl"));    // false
        trie.delete("apple");
    }
}