//
//  ChecklistItem.m
//  Checklists
//
//  Created by Justin Rubin on 6/1/14.
//  Copyright (c) 2014 Justin. All rights reserved.
//

#import "ChecklistItem.h"

@implementation ChecklistItem

- (void)toggleChecked
{
    self.checked = !self.checked;
}

@end
