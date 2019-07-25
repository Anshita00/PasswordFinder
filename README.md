# PasswordFinder
A program to crack the password in the open learning Week 8 Login leaks module for Security Engineering course

This is a small program to automate cracking a password for the following activity:

<b>You're a top secret spy and you've been able to partially compromise a secure government agency. After a successful and stealthy attack you have been able to leak parts of a high profile target's password.

You've got 1000 entries from the past 1000 times the user logged in - but there was a problem during the extraction. You couldn't get the full password - you could only get three characters of the password at a time.

However you do know something about these fragments - these characters definitely do appear in the password, the characters are always in the correct order, and due to the password policy of the agency, this user had to have a password with no repeating characters. 

You wonder whether you can reconstruct their full password from these fragments alone.
</b>

I copied all the 1000 lines into a text file. Then, I wrote a program to automate making a graph from these characters.

For example, if I see the characters ‘)’, ‘(’, ‘R’, I will add an edge in my graph from ) to (, and ( to R. Since the letters can’t be repeated, this gives me a directed acyclic graph. 

After getting the graph, I run the topological sort algorithm on it. I can do this because since the letters can’t be repeated in the password, there are no cycles in the graph. Why does the topological sort work? It’s because the topological sort algorithm gives me a linear ordering on a directed graph such that for every directed edge uv in the graph, the vertex u comes before v in my final topological order. Since each edge represents a character that comes before the next character in the password string, the final topological order will maintain this relationship.

 I also kept track of whether or not there were multiple topological sorts for this graph so that I can be certain that the topological sort I get is the only possible password. My program tells me that it is indeed the case, and I’ve successfully managed to crack the password! 
