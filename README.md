# My Personal Project
My personal project will be a type of personalized
wishlist for books with information on libraries. 
Think of a library website, where you can place books 
on hold, etc. but combined with multiple libraries 
near you, and customized for a user-based, **not**
 library-based experience. The application will store
a wish-list of books, and track whether you want to
read/are reading/have read each book. Will include a 
list of libraries the book is at, and whether it is 
checked out/in the library. Depending on how it goes,
I may want to include a calendar which shows date 
checked out/due date/date finished reading. It could
also have a way of recommending different books in a 
dataset based off of a book you like.
# Who and Why
Anyone who wants to make a wishlist of books, or even
just check out a book from a library could use this
application. Personally I would use a combination of 
all its uses, although maybe not the calendar. I have
always found library websites finicky and hard to use, 
and have disliked traditional book apps such as 
*GoodReads*. This application would combine all the 
useful information for library books into **one**.



## **Old User Stories**
- I want to be able to add a book to my wishlist, specify
  I want to read it, have read it, or am currently reading it,
- I want to be able to view a list of books in my wishlist
- I want to be able to change the status of a book on my 
wishlist to currently reading and completed.
- I want to be able to add a new library with a catalogue of 
books to a class/interface with libraries
- I want to be able to have the choice to save my Booklist before I end the program.
- I want to be able to reload a previous BookList when restarting my program.

## **Current User Stories**
- I want to be able to add a Book to my wishlist
- I want to be able to view my wishlist
- I want to be able to add a new book to my libraries
- I want to be able to view my libraries 

## Citation

"Code influced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
and Alarm System https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git




## Phase 4 Task 3:
If I were to refactor my design I would refactor WishList and Library into being the same class, 
I would do this because as time went on and I added more applications, Library and WishList became increasingly similar. 
In my current design I have multiple methods whose nearly only difference is the fact that one is working on a Library
and the other is working on the WishList. If I refactored the two to be the same class, I would be able to greatly 
simplify my gui methods, and refactor many into few. 
Additionally I may refactor my GUI into different classes, as I feel there is too much going on in the one class. I 
would potentially have different classes that add things to the screen, or change the libraries etc.
