import { useState } from 'react';

function Header({ user }) {
    const [showProfile, setShowProfile] = useState(false);

    const handleProfileButtonClick = () => {
        setShowProfile((prev) => !prev);
    };

    return (
        <header className="flex justify-between items-center p-5 shadow-md">
            <h1 className="text-xl font-semibold">
                OAuth2 React Client
            </h1>
            <div className="flex gap-4">
                {!!user &&
                    <button
                        className="py-1 px-2 border border-slate-800 rounded hover:bg-slate-100 relative"
                        onClick={handleProfileButtonClick}
                    >
                        Profile ({user.username})
                        {showProfile &&
                            <div className="absolute left-[-3px] mt-4 bg-white rounded-lg border outline-slate-200 shadow-xl p-6 w-[150%] text-left">
                                <div className="underline mb-1">Hobbies</div>
                                <ul>
                                    {user.hobbies?.map((hobby) => {
                                        return <li key={hobby}>{hobby}</li>
                                    })}
                                </ul>
                            </div>
                        }
                    </button>
                }
                <a className="py-1 px-2 border border-slate-800 rounded hover:bg-slate-100"
                    href={!!user ? "/oidc/logout" : "/oauth2/authorization/gateway"}
                >
                    {!!user ? "Log out" : "Log in"}
                </a>
            </div>
        </header>
    );
}

export default Header;