//
//  ChecklistsViewController.m
//  Checklists
//
//  Created by User on 5/29/14.
//  Copyright (c) 2014 Justin. All rights reserved.
//

#import "ChecklistsViewController.h"
#import "ChecklistItem.h"

@interface ChecklistsViewController ()

@end

@implementation ChecklistsViewController

{
    NSMutableArray *_items;  // declare the NSMutableArray variable
    // declared that a variable named _items can contain an NSMutableArray object
    // until an actual NSMutableArray object is instantiated and put into _items, the variable is empty (nil)
}

- (void) viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    _items = [[NSMutableArray alloc] initWithCapacity:20];  // create NSMutableArray object
    // has initial capacity of 20, but the array can grow to make more room
    
    ChecklistItem *item;  // create instance variable to hold data being added to "_items"
    
    // Create a ChecklistItem object and add it to the array "_items"
    item = [[ChecklistItem alloc] init];  // create a new CheckListItem object
    item.text = @"Walk the dog";  // load text into the object
    item.checked = NO; // load checked-state into the object
    [_items addObject:item];  // add this item to the array
    
    item = [[ChecklistItem alloc] init];
    item.text = @"Brush my teeth";
    item.checked = YES;
    [_items addObject:item];

    
    item = [[ChecklistItem alloc] init];
    item.text = @"Learn iOS development";
    item.checked = YES;
    [_items addObject:item];
    
    item = [[ChecklistItem alloc] init];
    item.text = @"Soccer practice";
    item.checked = NO;
    [_items addObject:item];
    
    item = [[ChecklistItem alloc] init];
    item.text = @"Eat ice cream";
    item.checked = YES;
    [_items addObject:item];
}

- (void) didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

// Return number of rows in the requested section
- (NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section  // Two parameters: "tableView" and "section"
// "tableview" is the UITableView object on whose behalf these methods are invoked
{
    return [_items count];  // returns the number of rows in "_items"
}

- (void) configureCheckmarkForCell:(UITableViewCell *)cell withChecklistItem:(ChecklistItem *)item
{
    if (item.checked) {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    } else {
        cell.accessoryType = UITableViewCellAccessoryNone;
    }
}

- (void) configureTextForCell:(UITableViewCell *)cell withChecklistItem:(ChecklistItem *)item
// sets the item's text on the cell's label
{
    UILabel *label = (UILabel *)[cell viewWithTag:1000];
    label.text = item.text;
}

// Method to obtain a cell for the particular row in question
- (UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
// Two parameters: "tableView" and "indexPath"
// "tableview" is the UITableView object on whose behalf these methods are invoked
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ChecklistItem"];
    
    ChecklistItem *item = _items[indexPath.row];
    // asks the array for the CheckListItem object at the index that corresponds with the row number
    // could also write as: ChecklistItem *item = [_items objectAtIndex:indexPath.row];
    
    // pull the two sets of data from the requested row of the array
    [self configureTextForCell:cell withChecklistItem:item];
    [self configureCheckmarkForCell:cell withChecklistItem:item];
    
    return cell;
}

- (void) tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
// method to toggle the "checked" state in the data model, then updates the view
{
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    
    ChecklistItem *item = _items[indexPath.row];
    // asks the array for the CheckListItem object at the index that corresponds with the row number
    
    [item toggleChecked];

    
    [self configureCheckmarkForCell:cell withChecklistItem:item];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (IBAction)addItem
// create a new CheckListItem object, add it to the data model (the _items array)
// figures out the row number of this new object and then tells the table view to update with the new row
{
    NSInteger newRowIndex = [_items count]; // index to add the new row to the end of the array at
    // works because the count is always 1 greater than the index, since index starts at 0
    
    // create new checklist item and add it to the end of the array
    ChecklistItem *item = [[ChecklistItem alloc] init];
    item.text = @"I am a new row";
    item.checked = NO;
    [_items addObject:item];
    
    // tell the table view about the new row so it can add a new cell for that row
    // first make an NSIndexPath object that points to the new row, using the row number from the newRowIndex variable
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:newRowIndex inSection:0];
    
    // create a new, temporary array that stores a single index-path object
    NSArray *indexPaths = @[indexPath];
    
    // tell the table view to insert this new row with an animation
    [self.tableView insertRowsAtIndexPaths:indexPaths withRowAnimation:UITableViewRowAnimationAutomatic];
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
// the presence of "commitEditingStyle" in view controller means table view will automatically enable swipe-to-delete
{
    [_items removeObjectAtIndex:indexPath.row];  // remove the item being deleted from the data model (deallocate)
    
    // delete the corresponding row from the table view
    NSArray *indexPaths = @[indexPath];
    [tableView deleteRowsAtIndexPaths:indexPaths withRowAnimation:UITableViewRowAnimationAutomatic];
}

@end