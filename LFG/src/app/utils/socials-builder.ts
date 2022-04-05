import { Social } from "../models/social.model";

export class SocialsBuilder {

    constructor() {}

    static buildSocials(data: any) {
        
        let userId: number = data.social.userid;
        let gameId: number = data.social.gameid;
        let gamertag: number = data.social.gamertag;
        let profileURL: string = data.steamProfile.response.players.player[0].profileurl;
        let avatar: string = data.steamProfile.response.players.player[0].avatar;
        let avatarMedium: string = data.steamProfile.response.players.player[0].avatarmedium;
        let avatarFull: string = data.steamProfile.response.players.player[0].avatarfull;
        let avatarHash: string = data.steamProfile.response.players.player[0].avatarhash;
        
        let socials: Social = new Social(userId, gameId, gamertag, profileURL, avatar, avatarMedium, avatarFull, avatarHash);
        return socials;
    }

    static checkSocials(data: any): boolean {
        if (data.steamProfile.response.players.player[0] == null) {
            return false;
        } else {
            return true;
        }
    }
}
