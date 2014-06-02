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
    ChecklistItem *_row0item;
    ChecklistItem *_row1item;
    ChecklistItem *_row2item;
    ChecklistItem *_row3item;
    ChecklistItem *_row4item;
}

- (void) viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    _row0item = [[ChecklistItem alloc] init];  // create a new CheckListItem object
    _row0item.text = @"Walk the dog";  // load text into the object
    _row0item.checked = NO; // load checked-state into the object
    
    _row1item = [[ChecklistItem alloc] init];
    _row1item.text = @"Brush my teeth";
    _row1item.checked = YES;
    
    _row2item = [[ChecklistItem alloc] init];
    _row2item.text = @"Learn iOS development";
    _row2item.checked = YES;
    
    _row3item = [[ChecklistItem alloc] init];
    _row3item.text = @"Soccer practice";
    _row3item.checked = NO;
    
    _row4item = [[ChecklistItem alloc] init];
    _row4item.text = @"Eat ice cream";
    _row4item.checked = YES;
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