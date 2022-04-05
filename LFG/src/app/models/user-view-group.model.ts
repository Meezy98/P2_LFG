import { Game } from "./game.model";

export class UserViewGroup {

    constructor(
        private _groupid: number,
        private _game: Game,
        private _maxusers: number,
        private _currentusers: number,
        private _description: string
    ){
        this.groupid = _groupid;
        this.game = _game;
        this.maxusers = _maxusers;
        this.currentusers = _currentusers;
        this.description = _description;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get currentusers(): number {
        return this._currentusers;
    }
    public set currentusers(value: number) {
        this._currentusers = value;
    }
    public get maxusers(): number {
        return this._maxusers;
    }
    public set maxusers(value: number) {
        this._maxusers = value;
    }
    public get game(): Game {
        return this._game;
    }
    public set game(value: Game) {
        this._game = value;
    }
    public get groupid(): number {
        return this._groupid;
    }
    public set groupid(value: number) {
        this._groupid = value;
    }

}
