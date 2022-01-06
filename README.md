# basic-investment-management
This program utilizes a GUI to manage, search and save basic investment portfolios.

General problem:
Creating a portfolio manager to manage a users investments (stock or mutualfunds), as well
as providing functions to enable the user to buy, sell, update, get the gain, or search 
within the portfolio. This assignment optimized the previous by creating a hashmap for the 
keyword search and using inheritance, additionally it allows file input and output. All
functionality on the front end is done through using a GUI.

Assumptions and limitations:
1. Assume initial file to read is formatted the same as the assignment outlines
2. Assume first file in command line is one to input


User guide:
Build by running Javac com.Assignment3/.*java then Java com.Assignment3.Portfolio
- A file name that has old portfolio data can be added to the command line to edit in the program
- If no file a new file "portfolio.txt" will be created
- The start menu will appear prompting the user to use the one of the 6 commands
- To begin some functionality will be resticted if the portfolio is empty
- Start by clicking buy in drop down menu to purchase stock if no command line arguments
- To input information type desired information and press enter
- After entering desired information press buy (if no errors purchase will be displayed to
messages)
- Now select desired input from the command menu to buy, sell, update the prices, get the gain,
search for an item, or exit
- To exit select quit in the command menu or close the window and to save to file select quit 
in the command menu
