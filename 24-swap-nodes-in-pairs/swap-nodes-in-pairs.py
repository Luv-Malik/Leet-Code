class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        dummy = ListNode(0, head)
        prev = dummy
        
        while prev.next and prev.next.next:
            first = prev.next
            second = prev.next.next
            
            # Swapping
            first.next = second.next
            second.next = first
            prev.next = second
            
            # Move pointer forward
            prev = first
            
        return dummy.next