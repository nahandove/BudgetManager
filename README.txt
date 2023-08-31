Project assignment from JetBrains Academy (www.hyperskill.org), Java Developer track.

This rather practical program records purchases and income and documents expenses either
in total or in subcategories. It is also capable of sorting expenses in descending order
thus allowing the user to keep track of which spending consumes the most of their income.

The program has eight main operations. The program continuously prompts and processes user
input until the user selects the exit option in the main menu.

The menu, as well as their associated submenus, are displayed as an ordered list, and the
user chooses the desired operation by inputing numbers. The main menu has eight items:

1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
5) Save
6) Load
7) Analyze (sort)
8) Exit

For several operations, sub-operations exist such that the user can also manage and track 
expenses under each sub-category of their purchases.

The features of each operation is described as below:

1) Add income: The user is prompted to input their income (which can include decimal points).
The amount is used to top up their budget.

2) Add purchase: this option leads the user to a submenu where the user is prompted to enter
the category of their purchases. The submenu is shown below:
	1) Food
	2) Clothes
	3) Entertainment
	4) Other
	5) Back
After the user selected the category, the user is prompted to enter the name of their purchase,
and then the price. The name can include space and numbers and other symbols, while the price
is a number that can include decimal points. The program stores the item which adds to the
overall expense, as well as expense in the particular subcategory. The user is continously
promted to define the category of each purchase and input new items until they select 5 (Back
operation), where they are returned to the main menu.

3) Show list of purchases: Similar to "Add purchase", this menu option takes the user to a 
submenu: the submenu includes the following options:
	1) Food
	2) Clothes
	3) Entertainment
	4) Other
	5) All
	6) Back
After a category is selected, all the purchases previously entered in a particular sub-category
are shown on the screen. In addition, the program tallies the overall expenses of this sub-
category. If the user selects option 5 (All), the program lists every purchase the user added
and sums up all expenses regardless of sub-category. The display follows a standard format:
First, the sub-category is shown as "category:". Then, the items are listed in the order of entry,
and in the format "item $price", where price is displayed to two digits after the decimal point.
Finally, the sum of all expenses in the particular sub-category is displayed. The program prompts
the user continuously and will display relevant information until the user enters 6 (Back), where
the user is returned to the main menu. 

4) Balance: The program deducts all expenses from the income and informs the user of remaining
money in their account.

5) Save: All purchases, expenses, and balances are saved in the provided text file
"purchases.txt".

6) Load: All entries from "purchases.txt" are loaded from a file. The user can again manage all
purchases and expenses they previously saved.

7) Sort: This menu also takes the user to a submenu. The submenu includes the following options:
	1) Sort all purchases
	2) Sort by type
	3) Sort certain type
	4) Back
The sorter always displays entries from the most expensive to the least, using the standard display
format "item $price". Similar to category 3, the sorter also displays the overall expense following
the sort operation. Option 1 sorts all purchases regardless of sub-category. Option 2 sorts the
overall expense of each sub-category, allowing the user to know which sub-category (food, clothes,
entertainment, other) eats up the most of their budget. Option 3 brings one to yet another submemu,
where the user is prompted to enter the sub-category to be sorted, and sorts only purchases in that
sub-category, after which the user is brought back to the sorting submenu. If the user enters 4
(Back), the user is returned to the main menu where they can perform other operations.

0) Exit: Selecting 0 from the main menu closes the program.

Special Note: if the user asks to display or sort a sub-category where no item exists, the program
informs the user that the sub-category is empty, and no item will be displayed or sorted. The user
is then returned to the submenu.

August 31th, 2023--description by E. Hsu (nahandove@gmail.com)
