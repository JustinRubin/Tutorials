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
    return 5;  // tells the table view the number of rows of data
}

// Method to obtain a cell for the particular row in question
- (UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath  // Two parameters: "tableView" and "indexPath"
// "tableview" is the UITableView object on whose behalf these methods are invoked
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ChecklistItem"];
 
    UILabel *label = (UILabel *)[cell viewWithTag:1000];  // tag = 1000 refers to the label in the table view cell for the view with tag 1000
    
    if (indexPath.row == 0) {
        label.text = _row0item.text;
    } 
    else if (indexPath.row == 1) {
        label.text = _row1item.text;
    }
    else if (indexPath.row == 2) {
        label.text = _row2item.text;
    }
    else if (indexPath.row == 3) {
        label.text = _row3item.text;
    }
    else if (indexPath.row == 4) {
        label.text = _row4item.text;
    }
    
    [self configureCheckmarkForCell:cell atIndexPath:indexPath];
    
    return cell;
}

- (void) configureCheckmarkForCell:(UITableViewCell *)cell atIndexPath:(NSIndexPath *)indexPath
{
    // initialize rows to be unchecked
    BOOL isChecked = NO;
    
    if (indexPath.row == 0) {
        isChecked = _row0item.checked;
    } else if (indexPath.row == 1) {
        isChecked = _row1item.checked;
    } else if (indexPath.row == 2) {
        isChecked = _row2item.checked;
    } else if (indexPath.row == 3) {
        isChecked = _row3item.checked;
    } else if (indexPath.row == 4) {
        isChecked = _row4item.checked;
    }
    
    // push the state of the row to the cell
    if (isChecked) {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    } else {
        cell.accessoryType = UITableViewCellAccessoryNone;
    }
}

- (void) tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
// method to toggle the "checked" state in the data model, then updates the view
{
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    
    if (indexPath.row == 0) {  // find the row in question
        _row0item.checked = !_row0item.checked;  // set the state to the opposite of its current state
    } else if (indexPath.row == 1) {
        _row1item.checked = !_row1item.checked;
    } else if (indexPath.row == 2) {
        _row2item.checked = !_row2item.checked;
    } else if (indexPath.row == 3) {
        _row3item.checked = !_row3item.checked;
    } else if (indexPath.row == 4) {
        _row4item.checked = !_row4item.checked;
    }
    
    [self configureCheckmarkForCell:cell atIndexPath:indexPath];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end