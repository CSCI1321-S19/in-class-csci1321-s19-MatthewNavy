package adt

import scala.collection.mutable

class BSTMap[K, V](cmp: (K, K) => Boolean) extends mutable.Map[K, V]
{
  import BSTMap.Node
  
  private var root: Node[K, V] = null
  
  def -=(key: K): this.type =
  {
    def removeNode(n: Node[K, V]): Node[K, V] =
    {
      if(n == null) n
      else
      {
        if(cmp(key, n.key)) 
        {
          n.left = removeNode(n.left)
          n
        }
        else if(cmp(n.key, key))
        {
          n.right = removeNode(n.right)
          n
        }
        else
        {
          if(n.left == null) n.right
          else if(n.right == null) n.left
          else
          {
            if(n.left.right == null)
            {
              n.left.right = n.right
              n.left
            }
            else
            {
              // helper goes here
              n
            }
          }
        }
      }
    }
    root = removeNode(root)
    this
  }
  
  def +=(kv: (K, V)): this.type =
  {
    def addNode(n: Node[K, V]): Node[K, V] =
    {
      if(n == null) new Node(kv._1, kv._2, null, null)
      else
      {
        if(cmp(kv._1, n.key)) n.left = addNode(n.left)
        else if(cmp(n.key, kv._1)) n.right = addNode(n.right)
        else n.value = kv._2
        n
      }
    }
    root = addNode(root)
    this
  }
  
  def get(key: K): Option[V] =
  {
    var rover = root
    while(rover != null && !cmp(rover.key, key) && !cmp(key, rover.key))
    {
      if(cmp(key, rover.key)) rover = rover.left
      else rover = rover.right
    }
    if(rover == null) None else Some(rover.value)
  }
  
  override def update(key: K, value: V): Unit = this += ((key, value))
  
  def iterator = new Iterator[(K, V)]
  {
    private val stack = new LinkedListStack[Node[K, V]]()
    private def pushAllLeft(n: Node[K, V]): Unit =
    {
      if(n != null)
      {
        stack.push(n)
        pushAllLeft(n.left)
      }
    }
    pushAllLeft(root)
    
    def hasNext(): Boolean = !stack.isEmpty
    def next(): (K, V) =
    {
      val ret = stack.pop()
      pushAllLeft(ret.right)
      ret.key -> ret.value
    }
  }
  
  def preorder(visit: (K, V) => Unit): Unit =
  {
    def recur(n: Node[K, V], v: (K, V) => Unit): Unit =
    {
      if(n != null)
      {
        v(n.key, n.value)
        recur(n.left, v)
        recur(n.right, v)
      }
    }
    recur(root, visit)
  }
  
  def postorder(visit: (K, V) => Unit): Unit =
  {
    def recur(n: Node[K, V], v: (K, V) => Unit): Unit =
    {
      if(n != null)
      {
        recur(n.left, v)
        recur(n.right, v)
        v(n.key, n.value)
      }
    }
    recur(root, visit)
  }
  
  def inorder(visit: (K, V) => Unit): Unit =
  {
    def recur(n: Node[K, V], v: (K, V) => Unit): Unit =
    {
      if(n != null)
      {
        recur(n.left, v)
        v(n.key, n.value)
        recur(n.right, v)
      }
    }
    recur(root, visit)
  }
  
}

object BSTMap
{
  class Node[K, V](val key: K, var value: V, var left: Node[K, V], var right: Node[K, V])
}