SELECT SUM(a.count)
FROM (
         SELECT COUNT(*) AS count
         FROM email_blacklist
         WHERE email = ?
         UNION ALL
         SELECT COUNT(*) AS count
         FROM email_domain_blacklist
         WHERE domain = ?
     ) a