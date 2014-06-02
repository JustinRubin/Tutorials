//
//  BullsEyeViewController.m
//  BullsEye
//
//  Created by Justin Rubin on 1/27/14.
//  Copyright (c) 2014 Aegle. All rights reserved.
//

#import "BullsEyeViewController.h"

@interface BullsEyeViewController ()

@end

@implementation BullsEyeViewController
{
    int _currentValue;  // create variable to store value of slider; will update every time the slider is moved
    int _targetValue;   // create variable to store the target value for the slider
    int _score;  // create variable to store the overall number of points the player has accumulated
    int _round;  // create variable to store the current round number
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self startNewGame];
    [self updateLabels];
    
    UIImage *thumbImageNormal = [UIImage imageNamed:@"SliderThumb-Normal"];
    [self.slider setThumbImage:thumbImageNormal forState:UIControlStateNormal];
    
    UIImage *thumbImageHighlighted = [UIImage imageNamed:@"SliderThumb-Highlighted"];
    [self.slider setThumbImage:thumbImageHighlighted forState:UIControlStateHighlighted];
    
    UIImage *trackLeftImage = [[UIImage imageNamed:@"SliderTrackLeft"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 14, 0, 14)];
    
    [self.slider setMinimumTrackImage:trackLeftImage forState:UIControlStateNormal];
    
    UIImage *trackRightImage = [[UIImage imageNamed:@"SliderTrackRight"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 14, 0, 14)];
    
    [self.slider setMaximumTrackImage:trackRightImage forState:UIControlStateNormal];
}

- (void) startNewRound
{
    _round += 1;  // increment the round number
    _targetValue = 1 + arc4random_uniform(100);  // creates a random integer number between 1 - 100
    _currentValue = 50;  // set "_currentValue" to the value of the slider when the app first opens (50)
    self.slider.value = _currentValue;
}

- (void) startNewGame
{
    _score = 0;
    _round = 0;
    [self startNewRound];
}

- (void) updateLabels  // updates the contents of the labels
{
    self.targetLabel.text = [NSString stringWithFormat:@"%d", _targetValue];
    self.scoreLabel.text = [NSString stringWithFormat:@"%d", _score];
    self.roundLabel.text = [NSString stringWithFormat:@"%d", _round];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)showAlert   // activates when player presses "Hit Me!" button
{
    int difference = abs(_currentValue - _targetValue);
    int points = 100 - difference;
    
    NSString *title;
    if (difference == 0)
    {
        title = @"Perfect!";
        points += 100;
    }
    else if (difference < 5)
    {
        title = @"You almost had it!";
        if (difference == 1)
        {
            points += 50;
        }
    }
    else if (difference < 10)
    {
        title = @"Pretty good!";
    }
    else
    {
        title = @"Note even close...";
    }
    
    _score += points;
    
    NSString *message = [NSString stringWithFormat:@"You scored %d points", points];
    
    UIAlertView *alertView = [[UIAlertView alloc]
                              initWithTitle:title
                              message:message  // displays the string with the value of the slider
                              delegate:self  // view controller is delegate of UIAlertView
                              cancelButtonTitle:@"OK"
                              otherButtonTitles:nil];  // creates alert message
    
    [alertView show];  // displays alert message
}

- (IBAction)sliderMoved:(UISlider *)slider
{
    _currentValue = lroundf(slider.value);  // assign current value of slider (rounded) to "_currentValue"
    // NSLog(@"The value of the slider is now: %f", slider.value);  // prints the value of the slier to the debug area
}

- (IBAction)startOver  // reset game entirely, start new game
{
    // Add crossfade
    CATransition *transition = [CATransition animation];
    transition.type = kCATransitionFade;
    transition.duration = 1;
    transition.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseOut];
    
    [self startNewGame];
    [self updateLabels];
    
    [self.view.layer addAnimation:transition forKey:nil];
}

- (void) alertView:(UIAlertView *)alertView didDismissWithButtonIndex:(NSInteger)buttonIndex
{
    [self startNewRound];  // starts a new round - newly randomized target value, and slider reset to default position
    [self updateLabels];
}

- (BOOL) prefersStatusBarHidden
{
    return YES;
}

@end