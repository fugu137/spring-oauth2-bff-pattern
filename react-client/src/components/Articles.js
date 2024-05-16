import { useEffect, useState } from "react"

function Articles() {
    const [articles, setArticles] = useState([]);

    useEffect(() => {
        fetch("/api/articles")
            .then(response => {
                return response.json();
            })
            .then((articles) => {
                console.log(articles);
                setArticles(articles);
            });
    }, []);

    return (
        <ul className="flex items-center flex-col gap-8">
            {articles.map((article) => {
                return <li key={article} className="bg-slate-200 w-[600px] p-6">{article}</li>
            })}
        </ul>
    );
}

export default Articles;