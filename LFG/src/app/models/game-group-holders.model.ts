import { GroupDetails } from "./group-details.model";
import { UserViewGroup } from "./user-view-group.model";

export class GameGroupHolders {
    constructor(
        private _gameId: number,
        private _selectedGameAvailableGroups: GroupDetails[]
    ){
        this.gameId = _gameId;
        this.selectedGameAvailableGroups = _selectedGameAvailableGroups;
    }
    public get selectedGameAvailableGroups(): GroupDetails[] {
        console.log(this._selectedGameAvailableGroups)
        return this._selectedGameAvailableGroups;
    }
    public set selectedGameAvailableGroups(value: GroupDetails[]) {
        this._selectedGameAvailableGroups = value;
    }
    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }

}
