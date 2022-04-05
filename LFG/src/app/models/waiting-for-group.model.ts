import { GroupUser } from "./group-user.model";

export class WaitingForGroup {
    
    private _waitingUsers: GroupUser[] = [];
    
    constructor() {}

    public get waitingUsers(): GroupUser[] {
        return this._waitingUsers;
    }
    public set waitingUsers(value: GroupUser[]) {
        this._waitingUsers = value;
    }

    addUser(interestedUser: GroupUser) {
        // Tell backend to add user to list and update host list

        // We will actually replace this WaitingGroups object with updated server data 
        // in case of other users or Group no longer existing
        this._waitingUsers.push(interestedUser);
    }

    removeUser(uninterestedUser: GroupUser) {
        this._waitingUsers.find(x => x !== uninterestedUser);
        // Tell server that user is no longer interested/has been declined
    }

    selectUser(selectedUser: GroupUser) {
        this.waitingUsers.find(x => x !== selectedUser);
        // Ensure server updates list
    }
}
