import { Game } from "./game.model";


export class GroupDetails {
    currentusers: any;
    maxusers:any;
    groupid:any;
    constructor (
        private _groupId: number,
        private _game: Game,
        private _maxUsers: number,
        private _currentSize: number,
        private _description: string
    ){
        this._groupId = _groupId;
        this._game = _game;
        this._maxUsers = _maxUsers;
        this._currentSize = _currentSize;
        this._description = _description;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get currentSize(): number {
        return this._currentSize;
    }
    public set currentSize(value: number) {
        this._currentSize = value;
    }
    public get maxUsers(): number {
        return this._maxUsers;
    }
    public set maxUsers(value: number) {
        this._maxUsers = value;
    }
    public get game(): Game {
        return this._game;
    }
    public set game(value: Game) {
        this._game = value;
    }
    public get groupId(): number {
        return this._groupId;
    }
    public set groupId(value: number) {
        this._groupId = value;
    }
    

    

}
