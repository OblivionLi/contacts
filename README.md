# contacts
#### A user-friendly phonebook management system that enables various actions, including adding individuals or organizations, editing any information related to the person/organization, removing contacts from the phonebook, tallying the number of records in the phonebook, displaying each record along with its corresponding fields, conducting searches for any record, and featuring a phone number validation function.
##### Example:
---
```
[menu] Enter action (add, list, search, count, exit): add
Enter the type (person, organization): organization
Enter the organization name: Car shop
Enter the address: Wall St. 3
Enter the number: +0 (123) 456-789-9999
The record added.

[menu] Enter action (add, list, search, count, exit): count
The Phone book has 1 records.

[menu] Enter action (add, list, search, count, exit): search
Enter search query: car
Found 1 results: 
1. Car shop

[search] Enter action ([number]), back, again: again
Enter search query: car
Found 1 results: 
1. Car shop

[search] Enter action ([number]), back, again: 1
Organization name: Car shop
Address: Wall St. 3
Number: +0 (123) 456-789-9999
Time created: 2023-08-26T13:02:36.253330
Time last edit: 2023-08-26T13:02:36.253330

[record] Enter action (edit, delete, menu): edit
Select a field (name, address, number):name
Enter name: New Car shop
Saved
Organization name: New Car shop
Address: Wall St. 3
Number: +0 (123) 456-789-9999
Time created: 2023-08-26T13:02:36.253330
Time last edit: 2023-08-26T13:03:30.429696600

[record] Enter action (edit, delete, menu): menu
[menu] Enter action (add, list, search, count, exit): search
Enter search query: new
Found 1 results: 
1. New Car shop

[search] Enter action ([number]), back, again: back

[menu] Enter action (add, list, search, count, exit): list
1. New Car shop

[list] Enter action ([number], back): 1
Organization name: New Car shop
Address: Wall St. 3
Number: +0 (123) 456-789-9999
Time created: 2023-08-26T13:02:36.253330
Time last edit: 2023-08-26T13:03:30.429696600


[record] Enter action (edit, delete, menu): edit
Select a field (name, address, number):number
Enter the number: +23 (321) 12-12 12 12
Saved
Organization name: New Car shop
Address: Wall St. 3
Number: +23 (321) 12-12 12 12
Time created: 2023-08-26T13:02:36.253330
Time last edit: 2023-08-26T13:04:45.117928700

[record] Enter action (edit, delete, menu): menu
[menu] Enter action (add, list, search, count, exit): exit
```
