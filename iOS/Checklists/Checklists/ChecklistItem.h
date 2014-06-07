//
//  ChecklistItem.h
//  Checklists
//
//  Created by Justin Rubin on 6/1/14.
//  Copyright (c) 2014 Justin. All rights reserved.
//

#import <Foundation/Foundation.h>

// ChecklistItem object serves to combine the text and the checked flag into one object

@interface ChecklistItem : NSObject

@property (nonatomic, copy) NSString *text;
@property (nonatomic, assign) BOOL checked;
- (void)toggleChecked;

@end
