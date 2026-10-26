class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        # Check if there are at least k nodes available
        curr = head
        count = 0
        while curr and count < k:
            curr = curr.next
            count += 1
            
        if count == k:
            # Reverse k nodes
            reversed_head = self.reverseLinkedList(head, k)
            # Recursively process the remaining list
            head.next = self.reverseKGroup(curr, k)
            return reversed_head
            
        return head

    def reverseLinkedList(self, head: ListNode, k: int) -> ListNode:
        prev, curr = None, head
        while k > 0:
            nxt = curr.next
            curr.next = prev
            prev = curr
            curr = nxt
            k -= 1
        return prev