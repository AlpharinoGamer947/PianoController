# PianoController V1

## Details

> This program coverts digital piano input to virtual keyboard
> and mouse inputs. This can be used for daily applications.
> Created for fun and entertainment.

## Caution

> This does not endorse **Cheating** or other malicious use of
> this program. Before using this on video games, ask the
> developer or studio for **permission** as this could be seen as
> a cheat tool by the games.

> **Use this at your own risk**

## Version 1.0: Keyboard Player

- recieve digital keyboard input
- convert midi data to virtual keyboard keys
- use virtual keys to play game

## Version 1.1:

> Skipped number when setting version number.

## Version 1.2: Visualization I

- Created UI PianoController
    - Octave: UI for 1 Octave of Piano Keys
        - Key (ParentClass): Builds a Key
            - WhiteKey: UI for White Piano Keys
            - BlackKey: UI for Black Piano Keys
    - OctaveFrame: Contains UI for group of Octaves and extra data

## Version 1.3: Dynamic Octaves

- Added the ability to add more Octaves before running the program.

## Version 1.4 (Current Development): Mouseing

- [x] convert midi to virtual mouse input
- [ ] Mouse Inputs in its own octave

## Version 1.X

- create settings to save user input data
- Ability to save and reuse data.
- Ability to choose MidiDevice

## Version 1.XX: Visualization II

- OctaveUI stays same size
  > Adds OctaveUI in a scrollpane so all the octaves can be
  > seen clearly.