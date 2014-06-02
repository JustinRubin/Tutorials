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
    
    // Instance variables to store row data
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
    
    return cell;
    
}

- (void) tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath  // what is the cell at this indexPath that has been passed along
{
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    
    if (cell.accessoryType == UITableViewCellAccessoryNone) {  // if the accessory type is "none", make a checkmark
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    }
    else {
        cell.accessoryType = UITableViewCellAccessoryNone;  // if it was a checkmark, make it "none"
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end