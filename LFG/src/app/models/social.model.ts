export class Social {

    constructor(
        private _userId: number, 
        private _gameId: number,  
        private _gamertag: number,
        private _profileURL: string,
        private _avatar: string,
        private _avatarMedium: string,
        private _avatarFull: string,
        private _avatarHash: string,
        ) 
        {
            this._userId = _userId,
            this._gameId = _gameId,
            this._gamertag = _gamertag,
            this._profileURL = _profileURL,
            this._avatar = _avatar,
            this._avatarMedium = _avatarMedium,
            this._avatarFull = _avatarFull,
            this._avatarHash = _avatarHash
        }

    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }

    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }

    public get gamertag(): number {
        return this._gamertag;
    }
    public set gamertag(value: number) {
        this._gamertag = value;
    }

    public get profileURL(): string {
        return this._profileURL;
    }
    public set profileURL(value: string) {
        this._profileURL = value;
    }

    public get avatar(): string {
        return this._avatar;
    }
    public set avatar(value: string) {
        this._avatar = value;
    }

    public get avaterMedium(): string {
        return this._avatarMedium;
    }
    public set avaterMedium(value: string) {
        this._avatarMedium = value;
    }

    public get avaterFull(): string {
        return this._avatarFull;
    }
    public set avaterFull(value: string) {
        this._avatarFull = value;
    }

    public get avaterHash(): string {
        return this._avatarHash;
    }
    public set avaterHash(value: string) {
        this._avatarHash = value;
    }

}
