//
//  ChecklistsViewController.m
//  Checklists
//
//  Created by User on 5/29/14.
//  Copyright (c) 2014 Justin. All rights reserved.
//

#import "ChecklistsViewController.h"

@interface ChecklistsViewController ()

@end

@implementation ChecklistsViewController

{
    // Instance variables to store string data
    NSString *_row0text;
    NSString *_row1text;
    NSString *_row2text;
    NSString *_row3text;
    NSString *_row4text;
    
    // Instance variables to store row state (checked or unchecked)
    BOOL _row0checked;
    BOOL _row1checked;
    BOOL _row2checked;
    BOOL _row3checked;
    BOOL _row4checked;
}

- (void) viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    _row0text = @"Walk the dog";
    _row1text = @"Brush teeth";
    _row2text = @"Learn iOS development";
    _row3text = @"Soccer practice";
    _row4text = @"Eat ice cream";
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
        label.text = _row0text;
    } 
    else if (indexPath.row == 1) {
        label.text = _row1text;
    }
    else if (indexPath.row == 2) {
        label.text = _row2text;
    }
    else if (indexPath.row == 3) {
        label.text = _row3text;
    }
    else if (indexPath.row == 4) {
        label.text = _row4text;
    }
    
    [self configureCheckmarkForCell:cell atIndexPath:indexPath];
    
    return cell;
    
}

- (void) configureCheckmarkForCell:(UITableViewCell *)cell atIndexPath:(NSIndexPath *)indexPath
{
    // initialize rows to be unchecked
    BOOL isChecked = NO;
    if (indexPath.row == 0) {
        isChecked = _row0checked;
    } else if (indexPath.row == 1) {
        isChecked = _row1checked;
    } else if (indexPath.row == 2) {
        isChecked = _row2checked;
    } else if (indexPath.row == 3) {
        isChecked = _row3checked;
    } else if (indexPath.row == 4) {
        isChecked = _row4checked;
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
        _row0checked = !_row0checked;  // set the state to the opposite of its current state
    } else if (indexPath.row == 1) {
        _row1checked = !_row1checked;
    } else if (indexPath.row == 2) {
        _row2checked = !_row2checked;
    } else if (indexPath.row == 3) {
        _row3checked = !_row3checked;
    } else if (indexPath.row == 4) {
        _row4checked = !_row4checked;
    }
    
    [self configureCheckmarkForCell:cell atIndexPath:indexPath];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end