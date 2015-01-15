IMPORT JAVA.IO.*;
IMPORT JAVA.NIO.FILE.*;

PUBLIC CLASS ALLCAPSDEMO {
    PUBLIC STATIC VOID MAIN(STRING[] ARGUMENTS) {
        IF (ARGUMENTS.LENGTH < 1) {
            SYSTEM.OUT.PRINTLN("YOU MUST SPECIFY A FILENAME AS AN ARGUMENT");
            SYSTEM.EXIT(-1);
        }
        ALLCAPS CAP = NEW ALLCAPS(ARGUMENTS[0]);
        CAP.CONVERT();
    }
}

CLASS ALLCAPS {
    STRING SOURCENAME;

    ALLCAPS(STRING SOURCEARG) {
        SOURCENAME = SOURCEARG;
    }

    VOID CONVERT() {
        TRY {
            // CREATE FILE OBJECTS
            PATH SOURCE = FILESYSTEMS.GETDEFAULT().GETPATH(SOURCENAME);
            PATH TEMP = FILESYSTEMS.GETDEFAULT().GETPATH("TMP_" + SOURCENAME);

            // CREATE INPUT STREAM
            FILEREADER FR = NEW FILEREADER(SOURCE.TOFILE());
            BUFFEREDREADER IN = NEW BUFFEREDREADER(FR);

            // CREATE OUTPUT STREAM
            FILEWRITER FW = NEW FILEWRITER(TEMP.TOFILE());
            BUFFEREDWRITER OUT = NEW
                BUFFEREDWRITER(FW);

            BOOLEAN EOF = FALSE;
            INT INCHAR;
            DO {
                INCHAR = IN.READ();
                IF (INCHAR != -1) {
                    CHAR OUTCHAR = CHARACTER.TOUPPERCASE( (CHAR)INCHAR );
                    OUT.WRITE(OUTCHAR);
                } ELSE
                    EOF = TRUE;
            } WHILE (!EOF);
            IN.CLOSE();
            OUT.CLOSE();

            FILES.DELETE(SOURCE);
            FILES.MOVE(TEMP, SOURCE);
        } CATCH (IOEXCEPTION|SECURITYEXCEPTION SE) {
            SYSTEM.OUT.PRINTLN("ERROR -- " + SE.TOSTRING());
        }
    }
}
