export class AvailableGames {

    constructor(
        private gameId: number,
        private name: string,
        private imgLink: string,
        private sessions: number
    ){
        this.gameId = gameId;
        this.name = name;
        this.imgLink = imgLink;
        this.sessions = sessions;
    }

    public get _gameId(): number {
        return this.gameId;
    }
    public set _gameId(value: number) {
        this.gameId = value;
    }
    public get currentGroups(): number {
        return this.sessions;
    }
    public set currentGroups(value: number) {
        this.sessions = value;
    }
    public get gameUrl(): string {
        return this.imgLink;
    }
    public set gameUrl(value: string) {
        this.imgLink = value;
    }
    public get gameName(): string {
        return this.name;
    }
    public set gameName(value: string) {
        this.name = value;
    }

    //gameId: number
    //gameName: string
    //gameUrl: string
    //currentGroups: number


}
