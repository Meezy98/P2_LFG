
import { GroupDetails } from "../models/group-details.model";
import { GroupUser } from "../models/group-user.model";
import { Group } from "../models/group.model";

export default class BuildGroup {

    constructor() { }

    static groupBuilder(data: any) {
        let groupId: number = data.groupId;
        let gameId: number = data.gameId;
        let groupLead: GroupUser = data.hostUser;
        let groupDetails: GroupDetails = data.sessionDetails;
        let groupMembers: GroupUser[] = data.groupMembers;

        let group: Group = new Group(groupId, gameId, groupLead, groupDetails, groupMembers);

        if (data.waitingMembers) {
            group.waitingUsers = data.waitingMembers;
        }
        return group;
    }

}
