import { UserViewGroup } from "./user-view-group.model";

export class ChosenGame {

    constructor(
        private _gameId: number,
        private _availableGroups: UserViewGroup[]
    ){
        this.gameId = _gameId;
        this.availableGroups = _availableGroups;
    }
    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }

    public get availableGroups(): UserViewGroup[] {
        return this._availableGroups;
    }
    public set availableGroups(value: UserViewGroup[]) {
        this._availableGroups = value;
    }
}
