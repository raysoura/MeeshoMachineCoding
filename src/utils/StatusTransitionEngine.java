package utils;

import constants.Status;

public class StatusTransitionEngine {

    public static Boolean isStatusTransitionAllowed(Status newState, Status oldStat) {
        switch (newState) {
            case RESOLVED:
                if (!oldStat.equals(Status.IN_PROGRESS)) {
                    return false;
                };

            case IN_PROGRESS: {
                if (oldStat.equals(Status.RESOLVED)) {
                    return false;
                }
            }
            case TODO: {
                if (oldStat.equals(Status.RESOLVED)) {
                    return false;
                }
            }
        }
        return true;
    }
}
