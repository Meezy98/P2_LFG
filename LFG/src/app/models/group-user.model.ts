export class GroupUser {

    private _groupId: number = 0;
    private _inGroup: boolean = false;
    
    constructor (
        private _username: string, 
    ){
        this._username = _username;;
    }

    public get username(): string {
        return this._username;
    }
    public set username(value: string) {
        this._username = value;
    }

    public get groupId(): number {
        return this._groupId;
    }
    public set groupId(value: number) {
        this._groupId = value;
    }

    public get inGroup(): boolean {
        return this._inGroup;
    }
    public set inGroup(value: boolean) {
        this._inGroup = value;
    }
}
