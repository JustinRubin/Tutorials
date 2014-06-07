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

- (void) configureCheckmarkForCell:(UITableViewCell *)cell atIndexPath:(NSIndexPath *)indexPath
{
    ChecklistItem *item = _items[indexPath.row];
    // asks the array for the CheckListItem object at the index that corresponds with the row number
    // could also write as: ChecklistItem *item = [_items objectAtIndex:indexPath.row];
    
    if (item.checked) {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    } else {
        cell.accessoryType = UITableViewCellAccessoryNone;
    }
}

// Method to obtain a cell for the particular row in question
- (UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
// Two parameters: "tableView" and "indexPath"
// "tableview" is the UITableView object on whose behalf these methods are invoked
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ChecklistItem"];
    
    ChecklistItem *item = _items[indexPath.row];
    // asks the array for the CheckListItem object at the index that corresponds with the row number
 
    UILabel *label = (UILabel *)[cell viewWithTag:1000];
    // tag = 1000 refers to the label in the table view cell for the view with tag 1000
    
    label.text = item.text;
    
    [self configureCheckmarkForCell:cell atIndexPath:indexPath];
    
    return cell;
}

- (void) tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
// method to toggle the "checked" state in the data model, then updates the view
{
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    
    ChecklistItem *item = _items[indexPath.row];
    // asks the array for the CheckListItem object at the index that corresponds with the row number
    item.checked = !item.checked;
    
    [self configureCheckmarkForCell:cell atIndexPath:indexPath];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end