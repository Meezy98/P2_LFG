import { Input } from "@angular/core";
import { GroupDetails } from "./group-details.model";
import { GroupUser } from "./group-user.model";
import { WaitingForGroup } from "./waiting-for-group.model";

export class Group {
    public get groupMembers(): GroupUser[] {
        return this._groupMembers;
    }
    public set groupMembers(value: GroupUser[]) {
        this._groupMembers = value;
    }
    public get groupDetails(): GroupDetails {
        return this._groupDetails;
    }
    public set groupDetails(value: GroupDetails) {
        this._groupDetails = value;
    }
    public get groupLead(): GroupUser {
        return this._groupLead;
    }
    public set groupLead(value: GroupUser) {
        this._groupLead = value;
    }
    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }
    public get groupId(): number {
        return this._groupId;
    }
    public set groupId(value: number) {
        this._groupId = value;
    }

    private _waitingUsers: GroupUser[] = [];
    // Add tag(s) when they're implemented

    constructor (
        private _groupId: number,
        private _gameId: number,
        private _groupLead: GroupUser,
        private _groupDetails: GroupDetails,
        private _groupMembers: GroupUser[]
        ){
        this.groupId = _groupId;
        this.gameId = _gameId;
        this.groupLead = _groupLead;
        this.groupDetails = _groupDetails;
        this.groupMembers = _groupMembers;
    }

    public get waitingUsers(): GroupUser[]{
        return this.waitingUsers;
    }

    public set waitingUsers(value: GroupUser[]){
        this._waitingUsers = value;
    }

    // addGroupMember(newMember: GroupUser) {
    //     if(this._groupMembers.length < this._groupDetails.maxUsers)
    //         this.waitingUsers.find(x => x !== newMember);
    //         this._groupMembers.push(newMember);
    //         // Tell server to add member to group and update waiting list
    //         if(this._groupMembers.length + 1 === this._groupDetails.maxUsers) {
    //             // tell server that group is full
    //             console.log("Group has filled!");
    //         }
    // }

    // removeGroupMember(currentMember: GroupUser) {
    //     this._groupMembers.filter(x => x !== currentMember);
    //     // Tell server that member has been removed
    // }

    // // This is for other users interterested in joining the group
    // addUserToWaitingList(interestedUser: GroupUser) {
    //     // Tell backend to add user to list and update host list
    //     this._waitingUsers.push(interestedUser);
    // }

    // // For both the host declining and other users canceling
    // removeUserFromWaitingList(uninterestedUser: GroupUser) {
    //     this._waitingUsers.find(x => x !== uninterestedUser);
    //     // Tell server that user is no longer interested/has been declined
    // }

    // Might not use these two methods as it may be better to get value change update from server
    // incrementGroupSize() {
    //     this._currentSize++
    // }
    // decrementGroupSize() {
    //     this._currentSize--
    // }

}
