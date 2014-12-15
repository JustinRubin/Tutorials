//
//  BullsEyeViewController.h
//  BullsEye
//
//  Created by Justin Rubin on 1/27/14.
//  Copyright (c) 2014 Aegle. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BullsEyeViewController : UIViewController <UIAlertViewDelegate>  // view controller is a delegate of UIAlertView

@property (nonatomic, weak) IBOutlet UISlider *slider;  // tells Interface Builder that there is an outlet named slider that can be connected to a UISlider object

@property (nonatomic, weak) IBOutlet UILabel *targetLabel;  // label that displays the target value on the screen

@property (nonatomic, weak) IBOutlet UILabel *scoreLabel;   // label that displays the players current score

@property (nonatomic, weak) IBOutlet UILabel *roundLabel;   // displays current round of the game

- (IBAction)showAlert;

- (IBAction)sliderMoved:(UISlider *)slider;

- (IBAction)startOver;

@end