package com.linkedlist;


/**
 * Represents a linked list.
 * <p> This Linked List is implemented circularly.</p>
 *
 * @param <T> generic type
 * @author Ahmad Fahmi
 * @version 1.1
 */
public class LinkedList<T> {

  private Node<T> head;
  private int size;

  /**
   * Creates a new LinkedList that has no node and have 0 size.
   */
  public LinkedList() {
    this.head = null;
    this.size = 0;
  }

  /**
   * Creates a new Linked list that has the first node containing value from the given input.
   *
   * @param value node value
   */
  public LinkedList(T value) {
    Node<T> node = new Node<>(value, this.head);
    this.size++;
  }

  /**
   * Creates a new LinkedList, copying the other LinkedList input.
   *
   * @param otherList other linked list
   */
  public LinkedList(LinkedList<T> otherList) {
    Node<T> address = otherList.head;
    this.head = null;
    this.size = otherList.getSize();

    if (address != null) {
      while (address.getNext() != otherList.getHead()) {
        this.add(address.getData());
        address.setNext(address.getNext());
      }
      this.add(address.getData());
    }
  }

  /**
   * Returns the size of the LinkedList.
   *
   * @return size
   */
  public int getSize() {
    return size;
  }

  /**
   * Returns the head of the linkedlist.
   *
   * @return head
   */
  public Node<T> getHead() {
    return head;
  }

  /**
   * Returns the index of the element contained in the list.
   * <p>
   * If the element is not in the list, return -1
   * </p>
   *
   * @param element element to be searched
   * @return index of element
   */
  public int find(T element) {
    int index = 0;
    int answer = -1;
    Node<T> temp = this.head;

    while (temp.getNext() != this.head) {
      if (temp.getData() == element) {
        answer = index;
        break;
      }
      index++;
      temp = temp.next;
    }

    if (temp.getData() == element) {
      answer = index;
    }

    return answer;
  }

  //    Get the index of the element contained in the list.
  //    If the element is not in the list, return -1.

  /**
   * Returns true if LinkedList empty, otherwise returns false.
   *
   * @return true if empty, otherwise false
   */
  public boolean isEmpty() {
    return this.head == null;
  }

  /**
   * Adds a new node with the element given by the input.
   *
   * @param element element to be inserted
   */
  public void add(T element) {
    Node<T> node = new Node<>(element, null);
    this.size++;
    if (this.isEmpty()) {
      this.head = node;
      this.head.next = this.head;
    } else {
      Node<T> last = this.head;
      while (last.getNext() != this.head) {
        last = last.getNext();
      }
      node.setNext(this.head);
      last.setNext(node);
    }
  }

  /**
   * Remove the first node containing the given element.
   *
   * @param element element to be removed
   */
  public void remove(T element) {
    Node<T> temp;
    Node<T> prev;

    if (!this.isEmpty()) {
      this.size--;
      if (this.head.getNext() == this.head) {
        this.head = null;
      } else {
        temp = this.head;
        prev = this.head;

        while (prev.getNext() != this.head) {
          prev = prev.getNext();
        }
        while (temp.getData() != element && temp.getNext() != this.head) {
          prev = temp;
          temp = temp.getNext();
        }
        if (prev.getNext() != this.head) {
          prev.setNext(temp.getNext());
        } else {
          this.head = temp.getNext();
          prev.setNext(this.head);
        }
      }
    }
  }

  /**
   * Returns the data of the node based on the given index of the LinkedList.
   *
   * @param idx index
   * @return data at index
   */
  public T get(int idx) {
    T answer;
    int i = 0;
    Node<T> temp = this.head;

    while (temp.getNext() != this.head) {
      if (i == idx) {
        answer = temp.data;
        break;
      }
      i++;

      temp = temp.getNext();
    }

    if (i == idx) {
      answer = temp.getData();
      return answer;
    } else {
      return null;
    }
  }

  /**
   * Represents the node of the linked list.
   */
  class Node<T> {

    public T data;
    public Node<T> next;

    /**
     * Creates a new node with no data and no next pointer.
     */
    public Node() {
      this.data = null;
      this.next = null;
    }

    /**
     * Creates a new node with data and the next pointer is given by the input.
     */
    public Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }

    /**
     * Returns the data stored in the node.
     *
     * @return data
     */
    public T getData() {
      return data;
    }

    /**
     * Changes the data stored in the node.
     */
    public void setData(T data) {
      this.data = data;
    }

    /**
     * Returns the next pointer of the node.
     */
    public Node<T> getNext() {
      return next;
    }

    /**
     * Changes the next pointer of the node.
     */
    public void setNext(Node<T> next) {
      this.next = next;
    }
  }
}
