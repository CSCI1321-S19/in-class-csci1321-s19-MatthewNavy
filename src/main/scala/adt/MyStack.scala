package adt

trait MyStack[A] 
{
  def peek(): A
  def pop(): A
  def push(elem: A)
  def isEmpty: Boolean
}